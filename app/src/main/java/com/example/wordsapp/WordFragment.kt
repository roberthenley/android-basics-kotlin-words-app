package com.example.wordsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentWordBinding

/**
 * A [Fragment] subclass to display a word and its description
 */
class WordFragment : Fragment() {
    private var _binding: FragmentWordBinding? = null
    private val binding get() = _binding!!
    private lateinit var word: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            word = it.getString(WordFragment.WORD).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Retrieve the lists of words and descriptions from res/values/arrays.xml
        val words = view.context.resources.getStringArray(R.array.words).toList()
        val descriptions = view.context.resources.getStringArray(R.array.word_descriptions).toList()
        val wordIndex = words.indexOf(word)
        val description = if (wordIndex == -1) "" else descriptions[wordIndex] ?: ""

        val headerText = binding.wordHeader
        headerText.text = word
        val descriptionText = binding.wordDescription
        descriptionText.text = description
        val homeButton = binding.homeButton
        homeButton.setOnClickListener {
            // Navigate to LetterListFragment, popping the stack
            val action = WordFragmentDirections.actionWordFragmentToLetterListFragment()
            view.findNavController().navigate(action)
        }
        // TODO: Add button to google search the word:
//        searchButton.setOnClickListener {
//            val queryUrl: Uri = Uri.parse("${WordListFragment.SEARCH_PREFIX}${item}")
//            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
//            context.startActivity(intent)
//        }
    }

    companion object {
        const val WORD = "word"
    }
}