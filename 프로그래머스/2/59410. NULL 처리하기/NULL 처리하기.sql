-- 코드를 입력하세요
SELECT ANIMAL_TYPE, COALESCE(NAME, 'No name'), SEX_UPON_INTAKE
FROM ANIMAL_INS 
ORDER BY ANIMAL_ID