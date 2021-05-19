package dev.alxminyaev.feature.event.di

import dev.alxminyaev.feature.event.usecase.eventkind.CreateEventKindUseCase
import dev.alxminyaev.feature.event.usecase.eventkind.GetEventKindListUseCase
import dev.alxminyaev.feature.event.usecase.outstudy.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val domainEventFeature = DI.Module(name = "domainEventFeature") {

    bind<CreateEventKindUseCase>() with provider { CreateEventKindUseCase(instance()) }
    bind<GetEventKindListUseCase>() with provider { GetEventKindListUseCase(instance()) }

    bind<CreateNewOutStudyEventUseCase>() with provider {
        CreateNewOutStudyEventUseCase(
            outStudyEventRepository = instance(),
            eventKindRepository = instance(),
            userRepository = instance(),
            chatRepository = instance()
        )
    }
    bind<GetListOutStudyEventUseCase>() with provider { GetListOutStudyEventUseCase(outStudyRepository = instance()) }

    bind<RegistrationUserOnOutStudyEvent>() with provider {
        RegistrationUserOnOutStudyEvent(
            userRepository = instance(),
            outStudyEventRepository = instance(),
            membersOutStudyEventRepository = instance(),
            requestOutStudyEventRepository = instance(),
            chatRepository = instance()
        )
    }
    bind<PutRequestOutStudyEventUseCase>() with provider {
        PutRequestOutStudyEventUseCase(
            userRepository = instance(),
            membersOutStudyEventRepository = instance(),
            outStudyEventRepository = instance(),
            requestOutStudyEventRepository = instance()
        )
    }

    bind<GetMemberOfOutStudyEventUseCase>() with provider {
        GetMemberOfOutStudyEventUseCase(
            membersOutStudyEventRepository = instance(),
            isOrganizerUC = instance(),
            userRepository = instance()
        )
    }
    bind<GetRequestsListByOutStudyEventUseCase>() with provider {
        GetRequestsListByOutStudyEventUseCase(
            userRepository = instance(),
            eventRepository = instance(),
            requestOutStudyEventRepository = instance()
        )
    }

    bind<IsOrganizerOfOutStudyEventUseCase>() with provider {
        IsOrganizerOfOutStudyEventUseCase(
            getOrganizerOfOutStudyEventUseCase = instance()
        )
    }
    bind<GetOrganizerOfOutStudyEventUseCase>() with provider {
        GetOrganizerOfOutStudyEventUseCase(
            outStudyEventRepository = instance(),
            userRepository = instance()
        )
    }

    bind<DeleteOutStudyEventUseCase>() with provider {
        DeleteOutStudyEventUseCase(
            outStudyEventRepository = instance()
        )
    }

    bind<GetOutStudyEventByIdUseCase>() with provider {
        GetOutStudyEventByIdUseCase(
            outStudyRepository = instance()
        )
    }

    bind<RewardMembersInOutStudyEventUseCase>() with provider {
        RewardMembersInOutStudyEventUseCase(
            eventRepository = instance(),
            userRepository = instance(),
            achievementRepository = instance(),
            criterionOutStudyRepository = instance()
        )
    }

    bind<ChangeStatusOutStudyEventUseCase>() with provider {
        ChangeStatusOutStudyEventUseCase(
            outStudyEventRepository = instance()
        )
    }
}
