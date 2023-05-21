package cloud.nasvit.boot.svc

import cloud.nasvit.boot.dto.EducationalInstitutionRequest
import cloud.nasvit.boot.dto.EducationalInstitutionResponse
import cloud.nasvit.boot.dto.DisciplineRequest
import cloud.nasvit.boot.dto.DisciplineResponse
import cloud.nasvit.boot.entity.EducationalInstitution
import cloud.nasvit.boot.entity.Discipline

interface EducationalInstitutionService {
    fun addEducationalInstitution(request: EducationalInstitutionRequest): EducationalInstitutionResponse
    fun getAllEducationalInstitutions(): MutableList<EducationalInstitution>
    fun deleteEducationalInstitutionById(id: Long)
    fun addDisciplineToEducationalInstitution(educationalInstitutionId: Long, disciplineId: Long): EducationalInstitution?
}

interface DisciplineService {
    fun addDiscipline(request: DisciplineRequest): DisciplineResponse
    fun getAllDisciplines(): MutableList<Discipline>
    fun deleteDisciplineById(id: Long)
    fun getDisciplineById(id: Long): DisciplineResponse
}