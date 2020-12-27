package dev.alxminyaev.feature.event.model.user

sealed class RoleInfo {

    abstract val id: Role
    abstract val name: String?
}

enum class Role(val id: Int) {
    UNKNOWN(0),
    PROFESSOR(1),
    STUDENT(2),
    TUTOR(3),
    ADMIN(4),
    OUT_STUDY_ORGANIZER(5),
    OUT_STUDY_MEMBER(6);


    companion object {
        fun valueById(id: Int): Role {
            return when (id) {
                PROFESSOR.id -> PROFESSOR
                STUDENT.id -> STUDENT
                TUTOR.id -> TUTOR
                ADMIN.id -> ADMIN
                OUT_STUDY_ORGANIZER.id -> OUT_STUDY_ORGANIZER
                OUT_STUDY_MEMBER.id -> OUT_STUDY_MEMBER
                else -> UNKNOWN
            }
        }

    }
}