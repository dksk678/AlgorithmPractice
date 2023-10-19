-- 코드를 입력하세요
SELECT ANIMAL_TYPE, 
    (case
    when name is null
    then 'No name'
    ELSE NAME
    END)
    as NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS 
ORDER BY ANIMAL_ID
