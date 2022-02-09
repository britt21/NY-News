package com.example.nytimes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.nytimes.databinding.FragmentNewsDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    val args by navArgs<NewsDetailsFragmentArgs>()
    lateinit var binding: FragmentNewsDetailsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsDetailsBinding.inflate(inflater)

        binding.titleTxt.setText(args.detailArgs.title)
        binding.bodyTxt.setText(args.detailArgs.abstract)
        binding.imageView.load(args.detailArgs.media[0].mediaMetadata[2].url)
        binding.realeaseDate.setText(args.detailArgs.publishedDate)
        binding.sourceUrl.setText(args.detailArgs.source)


        return binding.root

    }

}