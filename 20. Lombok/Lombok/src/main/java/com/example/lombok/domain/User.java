package com.example.lombok.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor  // parameter없이 생성되는 constructor ->  User user = new User();
@AllArgsConstructor  // 객체가 갖고 있는 모든 filed를 받아서 마드는 constructor -> User user = new User(name,  email~~);
@RequiredArgsConstructor  // 꼭 필요한 field만을 사용하는 constructor (필수 변수들은 @NonNull을 붙여서 설정)
@EqualsAndHashCode // equals method와 hashcode method를 overriding하기 위해 사용한다.
@Data // entity객체를 사용하며 가장 많이 사용하게 된다. (@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.를 모두 포함한다.)
@Builder // @AllArgsConstructor와 비슷하게 객체를 생성하고 field값을 주입해주는데 builder의 형식으로 제공해준다.
public class User {
    @NonNull // @RequiredArgsConstructor에서 사용될 field들을 지정정
    private String name;
    @NonNull
    private String email;

    private LocalDateTime createdAt; // 생성된 시간
    private LocalDateTime updatedAt; // 업데이트된 시간

//  아래와 같이 Getter Setter를 만드는 것은 @Getter, @Setter로도 만들 수 있다.
//   @Getter, @Setter는 class전체에 할 수도 있으며 특정 변수에만 지정할 수도 있다. (특정 변수에 할거면 변수위에 지정)
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    ......
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//

//    Lombok의 @ToString을 통해 아래의 코드를 대신할 수 있다.
//    @Override
//    public String toString() {
//        return "User{" +
//                "name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                '}';
//    }
}
