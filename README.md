# 아기보안관(음성인식과 객체인식을 이용한 농인 부모 육아 보조 앱)

라즈베리파이에서 아기 위치 감지, 울음 감지 후 울음 이유 분석 한 데이터를 파이어베이스 FCM을 이용하여 안드로이드 앱으로 알려주는 앱

라즈베리파이
- yolov5 : 아기 객체 감지
- Librosa : 녹음된 음악파일을 전처리
- CNN : 학습된 모델로 울음 분석(1st : 잡음인지 울음인지, 2st : 울음 종류 분석)

안드로이드 앱
- 알림 : 라즈베리파이로 부터 넘어온 데이터를 알려줌
- 체온 분석 : 현재 아기의 체온과 하루 체온 평균, 차트화
- 다이어리 : 아기 일기작성
- 지도 : 아기가 아플때 현재 진료 가능한 응급 병원을 알려줌
    -> 공공데이터를 활용하여 마커 표시와 리스트화

- 앱 소개
https://youtu.be/9pVVZbkBs4I
