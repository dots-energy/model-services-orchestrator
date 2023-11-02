FROM python:3.9.0

RUN mkdir /app/
WORKDIR /app

COPY fake_model/ ./fake_model/
COPY requirements.txt ./

RUN pip install -r requirements.txt

ENTRYPOINT python3 -m fake_model.main
