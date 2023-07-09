-- 코드를 입력하세요
-- options에서 통풍시트, 열선 시트, 가죽시트, 를 포함하는 경우를 찾은 후 CARTYPE으로 묶고, CNT로 수 세기,
SELECT CAR_TYPE, COUNT(CAR_ID) CARS
FROM CAR_RENTAL_COMPANY_CAR
WHERE OPTIONS LIKE '%시트%'
GROUP BY CAR_TYPE
ORDER BY CAR_TYPE
