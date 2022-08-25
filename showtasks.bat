call .\runcrud
if "%ERRORLEVEL%" == "0" goto showtasks
echo.
echo RUNCRUD has errors
goto fail

:showtasks
start http://localhost:8080/crud/v1/tasks

:fail
echo.
echo There were errors