package uz.uicgroup.presentation.screens.about.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.PagerParticipantsBinding
import uz.uicgroup.presentation.screens.about.pager.viewModel.ParticipantsViewModel
import uz.uicgroup.presentation.screens.about.pager.viewModel.impl.ParticipantsViewModelImpl

@AndroidEntryPoint
class ParticipantsPager : Fragment(R.layout.pager_participants) {
    private val viewModel: ParticipantsViewModel by viewModels<ParticipantsViewModelImpl>()
    private val binding by viewBinding(PagerParticipantsBinding::bind)
}