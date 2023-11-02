FROM python:3.9.0

RUN mkdir /app/
WORKDIR /app

COPY requirements.txt ./
RUN pip install -r requirements.txt

COPY model_services_orchestrator/ ./model_services_orchestrator/

ENTRYPOINT python3 -u -m model_services_orchestrator.main
