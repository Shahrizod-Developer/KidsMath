package uz.gita.kidsmath.presentation.ui.screen.fragment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Chronometer
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.graphics.green
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.kidsmath.R
import uz.gita.kidsmath.data.model.Correct
import uz.gita.kidsmath.data.model.Question
import uz.gita.kidsmath.data.model.Wrong
import uz.gita.kidsmath.data.room.entity.GameEntity
import uz.gita.kidsmath.data.shp.MySharedPreference
import uz.gita.kidsmath.databinding.DialogExitBinding
import uz.gita.kidsmath.databinding.DialogWinBinding
import uz.gita.kidsmath.databinding.ScreenGameBinding
import uz.gita.kidsmath.presentation.ui.viewmodel.GameScreenViewModel
import uz.gita.kidsmath.presentation.ui.viewmodel.impl.GameScreenViewModelImpl
import uz.gita.kidsmath.presentation.utils.Animation
import uz.gita.kidsmath.presentation.utils.ExplosionField
import uz.gita.kidsmath.presentation.utils.onClick
import uz.gita.kidsmath.presentation.utils.vibratePhone
import java.text.SimpleDateFormat
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random


@AndroidEntryPoint
class GameScreen : Fragment(R.layout.screen_game) {

    private val binding: ScreenGameBinding by viewBinding(ScreenGameBinding::bind)
    private val viewModel: GameScreenViewModel by viewModels<GameScreenViewModelImpl>()
    private lateinit var dateFormat: SimpleDateFormat
    private var totalTime: Long = 0
    private var startTime: Long = 0
    private val args: GameScreenArgs by navArgs()
    private lateinit var time: Chronometer
    private var number: Int = 0
    private var checkWin = 0
    private var star = 0
    private var k = 0
    private var a: Long = 0
    private var fX = 0f
    private var fY = 0f
    private var oldX = 0f
    private var oldY = 0f
    private var list = ArrayList<Question>()
    private var ansList = ArrayList<Int>()
    private var g = 0f
    private var h = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            showExitDialog()
        }
    }

    private fun showExitDialog() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            DialogExitBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        dialog.setCancelable(false)

        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(
                Color.TRANSPARENT
            )
        )

        dialogBinding.no.setOnClickListener {
            it.onClick {
                dialog.dismiss()
            }
        }

        dialogBinding.yes.setOnClickListener {

            it.onClick {
                viewModel.back()
                dialog.dismiss()
            }
        }

        dialog.setView(dialogBinding.root)
        dialog.show()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        number = args.question.number
        checkWin = args.question.count
        binding.count.text = "${1} / $checkWin"
        binding.level.text = number.toString()
        time = binding.time
        time.base = SystemClock.elapsedRealtime()
        startTime = SystemClock.elapsedRealtime()
        time.start()

        binding.ans1.Animation()
        binding.ans2.Animation()
        binding.ans3.Animation()
        binding.ans4.Animation()
        binding.ans5.Animation()
        binding.ans6.Animation()
        binding.ans7.Animation()

        binding.back.setOnClickListener {
            it.onClick {
                showExitDialog()
            }
        }

        viewModel.getByNumber(args.question.level, number)

        viewModel.gameModelLiveData.onEach {
            setQuestion(it[0])
            generateAns(it[0])
            list = it as ArrayList<Question>
        }.launchIn(lifecycleScope)


        binding.text.text = ""
        animate()

        binding.ans1.setOnTouchListener { v, event ->
            check(v, event, binding.ans1, binding.result)
            true
        }

        binding.ans2.setOnTouchListener { v, event ->
            check(v, event, binding.ans2, binding.result)
            true
        }

        binding.ans3.setOnTouchListener { v, event ->
            check(v, event, binding.ans3, binding.result)
            true
        }

        binding.ans4.setOnTouchListener { v, event ->
            check(v, event, binding.ans4, binding.result)
            true
        }
        binding.ans5.setOnTouchListener { v, event ->
            check(v, event, binding.ans5, binding.result)
            true
        }
        binding.ans6.setOnTouchListener { v, event ->
            check(v, event, binding.ans6, binding.result)
            true
        }
        binding.ans7.setOnTouchListener { v, event ->
            check(v, event, binding.ans7, binding.result)
            true
        }
        g = binding.ans7.x
        h = binding.ans7.y
    }


    private fun setQuestion(question: Question) {
        binding.numberOne.text = question.numberOne.toString()
        binding.numberTwo.text = question.numberTwo.toString()
        binding.operation.text = question.operation
    }


    @SuppressLint("SuspiciousIndentation")
    private fun check(
        view: View, event: MotionEvent, realTextView: TextView, targetTextView: TextView
    ) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                realTextView.z = 1f
                fX = event.x
                fY = event.y
                oldX = realTextView.x
                oldY = realTextView.y
            }
            MotionEvent.ACTION_MOVE -> {
                realTextView.z = 1f
                val dX = event.x - fX
                val dY = event.y - fY
                realTextView.x += dX
                realTextView.y += dY

                val dif = sqrt(
                    (realTextView.x - binding.result.x).toDouble().pow(2.0)
                            + (realTextView.y - binding.result.y).toDouble().pow(2.0)
                )
                if (realTextView.text.toString() == list[k].result.toString()
                    && dif < 100
//                    && abs(realTextView.x - oldX) > 300
//                    && abs(realTextView.y - oldY) > 300
                ) {
                    binding.result.text = realTextView.text
                    binding.ques.visibility = View.INVISIBLE
                    realTextView.x = binding.result.x
                    realTextView.y = binding.result.y
                }
            }
            MotionEvent.ACTION_UP -> {
                realTextView.z = 0f
                if (realTextView.text.toString() == list[k].result.toString()
                    && (realTextView.x > (binding.linear.x)
                            && realTextView.y > (binding.linear.y - binding.linear.height))
                ) {

                    view.isEnabled = false
                    binding.result.text = realTextView.text
                    binding.ques.visibility = View.INVISIBLE
                    realTextView.x = binding.result.x
                    realTextView.y = binding.result.y

                    viewLifecycleOwner.lifecycleScope.launch {

                        Correct.create(requireContext())
                        if (MySharedPreference.getInstance().music) Correct.mediaPlayer.start()
                        delay(700)
                        view.isEnabled = true
                        k++
                        if (k + 1 <= checkWin) {
                            binding.count.text = "${k + 1} / $checkWin"
                        }

                        realTextView.visibility = View.INVISIBLE
                        val mExplosionField = ExplosionField.attach2Window(activity)
                        mExplosionField.explode(binding.result)

                        viewLifecycleOwner.lifecycleScope.launch {
                            delay(700)
                            binding.result.visibility = View.VISIBLE
                            binding.ques.visibility = View.VISIBLE

                            realTextView.x = oldX
                            realTextView.y = oldY
                            realTextView.visibility = View.VISIBLE

                            if (k >= checkWin) {
                                showResultDialog()
                            } else {
                                setQuestion(list[k])
                                generateAns(list[k])
                            }
                        }
                    }
                } else {

                    if ((abs(targetTextView.y - realTextView.y) < 100
                                && abs(targetTextView.x - realTextView.x) < 100)
                        || (abs(binding.linear.y - realTextView.y) < 20
                                && abs(binding.linear.x - realTextView.x) < 20)
                    ) {
                        if (MySharedPreference.getInstance().music) {
                            Wrong.create(requireContext())
                            Wrong.mediaPlayer.start()
                        }
                        if (MySharedPreference.getInstance().vibration) vibratePhone()
                    }
                    realTextView.x = oldX
                    realTextView.y = oldY
                }
            }
        }
    }


    private fun generateAns(question: Question) {
        var shuffleList = ArrayList<Int>()

        when (question.result.toString().length) {
            in 1..2 -> {
                random(10, 99)
            }
            3 -> {
                random(100, 999)
            }
            4 -> {
                random(1000, 9999)
            }
        }

        shuffleList.clear()
        shuffleList = ansList
        shuffleList.add(question.result)
        shuffleList.shuffle()

        binding.ans1.z = 0f
        binding.ans2.z = 0f
        binding.ans3.z = 0f
        binding.ans4.z = 0f
        binding.ans5.z = 0f
        binding.ans6.z = 0f
        binding.ans7.z = 0f
        binding.ans1.text = shuffleList[0].toString()
        binding.ans2.text = shuffleList[1].toString()
        binding.ans3.text = shuffleList[2].toString()
        binding.ans4.text = shuffleList[3].toString()
        binding.ans5.text = shuffleList[4].toString()
        binding.ans6.text = shuffleList[5].toString()
        binding.ans7.text = shuffleList[6].toString()
    }

    private fun random(n: Int, m: Int) {
        ansList.clear()
        for (i in 0..5) {
            ansList.add(Random.nextInt(n, m))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showResultDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val binding = DialogWinBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        val alertDialog = builder.create()

        totalTime = SystemClock.elapsedRealtime()
        time.stop()


        dateFormat = SimpleDateFormat("sss")
        a = abs(startTime - totalTime)
        binding.time.text = "Time : " + a / 1000 + " second"
        when (args.question.level) {

            1 -> {
                when (a) {
                    in 0..31000 -> {
                        star = 3
                    }
                    in 31000..46000 -> {
                        star = 2
                    }
                    in 46000..61000 -> {
                        star = 1
                    }
                }
            }
            2 -> {
                when (a) {
                    in 0..92000 -> {
                        star = 3
                    }
                    in 92000..122000 -> {
                        star = 2
                    }
                    in 122000..1520000 -> {
                        star = 1
                    }
                }
            }
            3 -> {
                when (a) {
                    in 0..183000 -> {
                        star = 3
                    }
                    in 183000..213000 -> {
                        star = 2
                    }
                    in 213000..243000 -> {
                        star = 1
                    }
                }
            }
        }

        if (number != 59 && star > 0) {

            binding.next.alpha = 1f
            binding.empty.visibility = View.GONE
            binding.next.isClickable = true


            when (star) {
                1 -> {
                    binding.starOne.visibility = View.VISIBLE
                    binding.starTwo.visibility = View.GONE
                    binding.starThree.visibility = View.GONE
                }
                2 -> {
                    binding.starOne.visibility = View.VISIBLE
                    binding.starTwo.visibility = View.VISIBLE
                    binding.starThree.visibility = View.GONE
                }
                3 -> {
                    binding.starOne.visibility = View.VISIBLE
                    binding.starTwo.visibility = View.VISIBLE
                    binding.starThree.visibility = View.VISIBLE
                }
            }

            viewModel.saveResult(
                GameEntity(
                    id = args.question.id,
                    questionList = args.question.questionList,
                    number = number,
                    star = star,
                    time = totalTime,
                    state = true,
                    level = args.question.level,
                    count = args.question.count
                )
            )
            number++
        } else {
            binding.next.alpha = 0.7f
            binding.empty.visibility = View.VISIBLE
            binding.next.isClickable = false
        }

        binding.quit.setOnClickListener {
            viewModel.openNextLevel(args.question.level, number)
            viewModel.back()
            alertDialog.dismiss()
        }

        alertDialog.window!!.setBackgroundDrawable(
            ColorDrawable(
                Color.TRANSPARENT
            )
        )

        alertDialog.show()
    }


    private fun animate() {

        binding.text.animate()
            .setDuration(1200)
            .translationY(-350f).withEndAction { binding.text.visibility = View.GONE }.start()
    }

}