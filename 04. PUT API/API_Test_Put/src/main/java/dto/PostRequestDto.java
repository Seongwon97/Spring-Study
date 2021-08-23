package dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

// 앞서 Post에서 배웠던 @JsonProperty는 한가지 변수에 대해 이름을 지어준 것이라면
// @JsonNaming은 class내에 있는 모든 변수에 대해 이름을 mapping해준다.
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PostRequestDto {

    private String name;
    private int age;
    private List<CarDto> carDtoList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<CarDto> getCarList() {
        return carDtoList;
    }

    public void setCarList(List<CarDto> carDtoList) {
        this.carDtoList = carDtoList;
    }

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", carList=" + carDtoList +
                '}';
    }
}
