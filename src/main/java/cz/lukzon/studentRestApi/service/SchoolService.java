package cz.lukzon.studentRestApi.service;

import cz.lukzon.studentRestApi.entity.School;
import cz.lukzon.studentRestApi.exception.ConflictException;
import cz.lukzon.studentRestApi.exception.DataNotFoundException;
import cz.lukzon.studentRestApi.exception.ProccesingRejectedException;
import cz.lukzon.studentRestApi.idto.SchoolIDTO;
import cz.lukzon.studentRestApi.repository.SchoolRepository;
import cz.lukzon.studentRestApi.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School getSchoolById(Long schoolId) throws DataNotFoundException {
        return schoolRepository.findById(schoolId)
                .orElseThrow(() -> new DataNotFoundException("This school is not exists"));
    }

    public List<School> getAllSchools() throws DataNotFoundException {
        List<School> schools = schoolRepository.findAll();
        if (schools.isEmpty()) {
            throw new DataNotFoundException("No schools were found");
        }
        return schools;
    }

    public void addNewSchool(SchoolIDTO schoolIDTO) throws ProccesingRejectedException, ConflictException {
        String name = schoolIDTO.getName();
        String town = schoolIDTO.getTown();

        if (Utils.isNotStringValid(name)) {
            throw new ProccesingRejectedException("Name is not valid, must contains more then 3 characters!");
        }

        if (Utils.isNotStringValid(town)) {
            throw new ProccesingRejectedException("Town is not valid, must contains more then 3 characters!");
        }

        Optional<School> schoolOptional = schoolRepository.findSchoolByName(name);
        if (schoolOptional.isPresent()) {
            throw new ConflictException("This school is exists!");
        }

        schoolRepository.save(new School(name, town));
    }

    @Transactional
    public void updateSchool(Long schoolId, SchoolIDTO schoolIDTO)
            throws DataNotFoundException, ProccesingRejectedException, ConflictException {
        String name = schoolIDTO.getName();
        String town = schoolIDTO.getTown();
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new DataNotFoundException("This school is not exists"));

        if (Utils.isNotStringValid(name)) {
            throw new ProccesingRejectedException("Name is not valid, must contains more then 3 characters!");
        }

        if (Utils.isNotStringValid(town)) {
            throw new ProccesingRejectedException("Town is not valid, must contains more then 3 characters!");
        }

        Optional<School> schoolOptional = schoolRepository.findSchoolByName(name);
        if (schoolOptional.isPresent() && !Objects.equals(name, school.getName())) {
            throw new ConflictException("This school with this name is exists");
        }

        school.setName(name);
        school.setTown(town);
    }

    public void deleteSchool(Long schoolId) throws DataNotFoundException {
        if (!schoolRepository.existsById(schoolId)) {
            throw new DataNotFoundException("This school is not exists");
        }
        schoolRepository.deleteById(schoolId);
    }
}
