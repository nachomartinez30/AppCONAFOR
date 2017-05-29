SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [Clave_UMAFOR]=2010 and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc



SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [idEstado]=20 and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc
--ECOREGIONES

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [Eco_N4]='13.5.2.2' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [Eco_N3]='13.5.2' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [Eco_N2]='13.5' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [Eco_N1]='13' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc


---cambio fuente


SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [idEstado]=20 and [Eco_N4]='13.5.2.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT 
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [idEstado]=20 and [Fuente]='1erINF61-85' 
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc


--ECOREGIONES

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [Eco_N4]='13.5.2.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [Eco_N3]='13.5.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [Eco_N2]='13.5' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra lanata' and [Eco_N1]='13' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc


--COMPILADO
--...

--GENERO


SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [Clave_UMAFOR]=2010 and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc



SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [idEstado]=20 and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc
--ECOREGIONES

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [Eco_N4]='13.5.2.2' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [Eco_N3]='13.5.2' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [Eco_N2]='13.5' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [Eco_N1]='13' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc


---cambio fuente


SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [idEstado]=20 and [Eco_N4]='13.5.2.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [idEstado]=20 and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

--ECOREGIONES

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [Eco_N4]='13.5.2.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [Eco_N3]='13.5.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [Eco_N2]='13.5' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Nombre_APG] ='Clethra' and [Eco_N1]='13' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc


--FAMILIA


SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [Clave_UMAFOR]=2010 and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc



SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [idEstado]=20 and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc
--ECOREGIONES

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [Eco_N4]='13.5.2.2' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [Eco_N3]='13.5.2' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [Eco_N2]='13.5' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [Eco_N1]='13' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc


---cambio fuente


SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [idEstado]=20 and [Eco_N4]='13.5.2.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [idEstado]=20 and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

--ECOREGIONES

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [Eco_N4]='13.5.2.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [Eco_N3]='13.5.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [Eco_N2]='13.5' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Familia_APG] ='Clethraceae' and [Eco_N1]='13' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

--AFINIDAD A GENERO


SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Genero_APG] ='Clethra' and [Clave_UMAFOR]=2010 and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Genero_APG] ='Clethra' and [Eco_N4]='13.5.2.2' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Genero_APG] ='Clethra' and [Eco_N3]='13.5.2' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Genero_APG] ='Clethra' and [Eco_N2]='13.5' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Genero_APG] ='Clethra' and [Eco_N1]='13' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Genero_APG] ='Clethra' and [idEstado]=20 and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Genero_APG] ='Clethra' and [Eco_N4]='13.5.2.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Genero_APG] ='Clethra' and [Eco_N3]='13.5.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Genero_APG] ='Clethra' and [Eco_N2]='13.5' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE [Genero_APG] ='Clethra' and [Eco_N1]='13' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

--ECUACIONES GENERALES



SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE  [Familia_APG] ='Clethraceae' and [Clave_UMAFOR]=2010 and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE  [Familia_APG] ='Clethraceae' and [idEstado]=20 and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc
--


SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE  [Familia_APG] ='Clethraceae' and [Eco_N4]='13.5.2.2' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE  [Familia_APG] ='Clethraceae' and [Eco_N3]='13.5.2' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE  [Familia_APG] ='Clethraceae' and [Eco_N2]='13.5' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE  [Familia_APG] ='Clethraceae' and [Eco_N1]='13' and [Fuente]='Siplafor'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc


---cambio fuente


SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE  [Familia_APG] ='Clethraceae' and [idEstado]=20 and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

--ECOREGIONES

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE  [Familia_APG] ='Clethraceae' and [Eco_N4]='13.5.2.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE  [Familia_APG] ='Clethraceae' and [Eco_N3]='13.5.2' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE  [Familia_APG] ='Clethraceae' and [Eco_N2]='13.5' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc

SELECT DISTINCT
MAX(Num_Observaciones) as Max_Num_Observaciones,
[Clave_ecuacion]
FROM [ArbolDesiciones].[dbo].[Ecuaciones_VRTA_2]
WHERE  [Familia_APG] ='Clethraceae' and [Eco_N1]='13' and [Fuente]='1erINF61-85'
Group by [Clave_ecuacion]
Order by Max_Num_Observaciones Desc


--Clave CM

--CM_0.3

