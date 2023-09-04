# ERD

주식 모의투자 게임


# Git Rule

# Git Convention

master, verX.X, front, back, feat(기능) 총 5가지로 구분하며 세부 내용은 다음과 같습니다.

- `master` : 서비스를 출시할 수 있는 메인 브랜치입니다. 에러를 확인 후 최종적으로 올리는 단 하나의 결과를 가집니다.
- `ver0.0.1` : 실제 개발이 이루어지는 브랜치입니다. 서비스 출시 전 코드를 통합하여 기능에 따라 버전을 관리합니다.
- `front`, `back` : 각 버전에서 분기하여 프론트(front-end)와 백(back-end)을 구분하는 브랜치입니다. 기능을 구현하여 해당 브랜치로 통합(merge)합니다.
- `feat` : 기능 단위 개발을 위한 브랜치입니다.(예시 - `login`) front, back 브랜치에서 분기되어 개발이 끝나면 해당 베이스 브랜치로 병합시킨 후 삭제되는 임시 브랜치입니다.

```
master
└── ver0.1.0 ── ver0.2.0
                                ├── front
                                │   └── login(feature)
                                │   └── signup(feature)
                                |
                                └── back
                                        └── ...
```

기능이 완성되면 front, back 브랜치로 병합하여 오류가 없는지 등을 체크합니다.

⚠️ !!! merge 전에는 **반드시** 해당 파트 팀원들과 공유합니다!!!!!

---

## 커밋(Commit) 관리

- 메인: `[Front] (feature name)`
- 세부 내용: `~~기능 추가 / - ~~ 부분 추가` 등
    - git commit –amend ➡ i 누르면 수정모드 ➡ 세부 내용 수정 후 `esc` >> `shift+ ;` >> `wq`
    - git log –oneline –gragh : 로그 한눈에 보기 쉬움

(예시)

```
[Front] 로그인 기능 구현
- 홈 화면에 버튼 추가
- 카카오 API 추가
```

---

# Git Flow 참고

[우린 Git-flow를 사용하고 있어요 | 우아한형제들 기술블로그](https://techblog.woowahan.com/2553/)


Frontend

- components 이름은 pascal case 적용(첫 글자 대문자)
- Non-components 이름은 Camel case
- Unit test 파일명은 대상 파일명과 동일하게 작성
- 속성명은 Camel case로 작성
- inline 스타일은 Camel case로 작성
- 컨벤션 관련 링크

[[react] react 코딩 컨벤션](https://phrygia.github.io/react/2022-04-05-react/)

Backend

- 기본 변수는 Camel case 적용
    
    ex) int addNum;
    
- const, final 사용 시 모두 대문자로 적용
    
    ex) final int NUMBER;
    
- public, private은 구분하지 않는다
- 클래스 이름의 첫글자는 대문자로
    
    ex) class Car; class StudentManager;
    
- 메소드 이름 Camel case 적용
    
    ex) void printName();



# 팀원 소개

- Frontend Team

|이름|직책|담당 업무|
|------|---|---|
|이연주|||
|류나연|||

- Backend Team

 |이름|직책|담당 업무|
|------|---|---|
|김태현|팀장||
|최우석|||
|최규헌|||
|김현식|||


