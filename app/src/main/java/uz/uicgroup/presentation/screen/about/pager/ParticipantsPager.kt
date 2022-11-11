package uz.uicgroup.presentation.screen.about.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.presentation.screen.about.pager.viewModel.ParticipantsViewModel
import uz.uicgroup.presentation.screen.about.pager.viewModel.impl.ParticipantsViewModelImpl

@AndroidEntryPoint
class ParticipantsPager : Fragment() {
    private val viewModel: ParticipantsViewModel by viewModels<ParticipantsViewModelImpl>()
}