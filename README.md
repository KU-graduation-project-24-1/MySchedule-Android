![ic_launcher](https://github.com/KU-graduation-project-24-1/MySchedule-Android/assets/72340294/b08aa7ca-9df4-4ee4-98e0-fabfaef12b12)

# 오늘알바
- 알바와 사장이 같이 쓰는 근무 스케줄링 애플리케이션, 오늘알바
- 각자 근무시간과 가능한 시간을 입력하고 이를 토대로 매월 8일 자동으로 스케줄링을 진행
- 해당 근무일에 대타요청을 통해 스케줄 변경 가능


# Development
## Required
- IDE : Android Studio Iguana

- JDK : Java 17을 실행할 수 있는 JDK

- Kotlin Language : 1.9.0

## Language
Kotlin 100%


# Architecture
## UI Layer

상태는 아래로 이동하고 이벤트는 위로 이동하는 단방향 데이터 흐름 사용.
더 자세한 내용은 [Compose UI Architecture](https://developer.android.com/develop/ui/compose/architecture?hl=ko)
## Data Layer

- [안드로이드 권장 아키텍처](https://developer.android.com/topic/architecture?hl=ko) 사용

![오늘알바 안드로이드 아키텍처 구조](https://github.com/KU-graduation-project-24-1/MySchedule-Android/assets/72340294/f511426b-f5aa-4bf1-9978-8e9f70ceb8aa)

# Features
## 공통 화면
- 해당 월의 전체 근무 스케줄링을 캘린더로 확인할 수 있습니다.
- 원하는 날짜를 클릭하면 더 자세한 근무 정보를 확인할 수 있습니다.
- 대타를 요청하거나 수락할 수 있습니다.
  
|근무 정보 확인, 대타요청,수락|
|:--:|
|<img src = "https://github.com/KU-graduation-project-24-1/MySchedule-Android/assets/72340294/c48c4a9f-3509-4318-aa18-2a90f05b0ca5" width="252" height="560">|

## 피고용인 화면
- 해당 달의 가능한 시간을 추가할 수 있습니다. 이 정보를 바탕으로 스케줄링이 이루어집니다.
- 고정적으로 가능한 시간은 마이페이지에서 추가할 수 있습니다.

|비고정적 가능한 시간 추가|고정적 가능한 시간 추가|
|:--:|:--:|
|<img src = "https://github.com/KU-graduation-project-24-1/MySchedule-Android/assets/72340294/845b1c0f-844b-4ee2-a7d9-4c5a84e62359" width="252" height="560">|<img src = "https://github.com/KU-graduation-project-24-1/MySchedule-Android/assets/72340294/26061794-b353-446d-82a9-a78fe83065ff" width="252" height="560">|

## 고용인 화면
- 자동으로 이루어진 스케줄링에 새로운 스케줄을 추가하거나 기존 스케줄을 변경,삭제할 수 있습니다.
- 피고용인의 고용형태를 변경하거나 삭제할 수 있습니다.
- 가게의 영업 정보와 요일별 필요한 근무 인원을 작성할 수 있습니다. 이 정보를 바탕으로 스케줄링이 이루어집니다.

|스케줄 추가|스케줄 변경,삭제|고용형태 수정,삭제|가게 영업 정보 및 필요 근무 인원 작성|
|:--:|:--:|:--:|:--:|
|<img src = "https://github.com/KU-graduation-project-24-1/MySchedule-Android/assets/72340294/480315e0-5aed-46d8-afca-3cadcc93f75a" width="252" height="560">|<img src = "https://github.com/KU-graduation-project-24-1/MySchedule-Android/assets/72340294/480315e0-5aed-46d8-afca-3cadcc93f75a" width="252" height="560">|<img src = "https://github.com/KU-graduation-project-24-1/MySchedule-Android/assets/72340294/1b258f5a-a93d-4b87-8668-fd82374d29b9" width="252" height="560">|<img src = "https://github.com/KU-graduation-project-24-1/MySchedule-Android/assets/72340294/d7fe6ab3-d6b1-40c8-bbac-bb5c3493efe0" width="252" height="560">|
