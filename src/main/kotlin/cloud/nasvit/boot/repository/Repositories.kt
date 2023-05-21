package cloud.nasvit.boot.repository


import org.springframework.data.jpa.repository.JpaRepository
import cloud.nasvit.boot.entity.EducationalInstitution
import cloud.nasvit.boot.entity.Discipline

interface EducationalInstitutionRepository: JpaRepository<EducationalInstitution, Long>
interface DisciplineRepository: JpaRepository<Discipline, Long>