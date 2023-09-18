package tech.nimish.Springboot.tutorial.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tech.nimish.Springboot.tutorial.entity.Department;
import tech.nimish.Springboot.tutorial.repository.DepartmentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                .id(1L)
                .code("DEP001")
                .address("NYC1")
                .name("IT")
                .build();

        Mockito.when(departmentRepository.findByNameIgnoreCase("IT"))
                        .thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on valid department name")
    @Disabled
    public void whenValidDepartmentName_thenDepartmentShouldFound() {
        String name = "IT";
        Department found = departmentService.fetchDepartmentByName(name);

        assertEquals(name, found.getName());
    }

}