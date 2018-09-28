call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startsite
echo.
echo runcrud.bat error
goto fail

:startsite
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open the site

:fail
echo.
echo There were errors

:end
echo.
echo Tasks presented