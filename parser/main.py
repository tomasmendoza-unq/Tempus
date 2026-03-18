from fastapi import FastAPI, UploadFile, File
import pdfplumber
import re
import io

app = FastAPI()

def limpiar(texto):
    if not texto:
        return ""
    return texto.replace("\n", " ").strip()

def extraer_modalidad(comision):
    match = re.search(r"\((.+?)\)$", comision.strip())
    if match:
        modalidad = match.group(1).strip()
        codigo = comision[:match.start()].strip()
        return codigo, modalidad
    return comision.strip(), ""

def normalizar_horario(horario):
    if not horario:
        return horario
    # Solo insertar " / " cuando un día aparece después de un dígito o ")"
    return re.sub(r'(\d|\))\s+(Lun|Mar|Mie|Jue|Vie|Sab|Sáb|Dom)\s', r'\1 / \2 ', horario).strip()

@app.post("/parsear")
async def parsear_pdf(file: UploadFile = File(...)):
    contenido = await file.read()
    resultados = []

    with pdfplumber.open(io.BytesIO(contenido)) as pdf:
        for page in pdf.pages:
            tabla = page.extract_table()
            if not tabla:
                continue
            for fila in tabla:
                if not fila or len(fila) < 3:
                    continue
                actividad = limpiar(fila[0])
                comision  = limpiar(fila[1])
                horario   = normalizar_horario(limpiar(fila[2]))

                if not actividad or not comision:
                    continue
                if "Actividad" in actividad:
                    continue
                if not re.search(r"\d+-\d+-\w+", comision):
                    continue

                codigo, modalidad = extraer_modalidad(comision)

                resultados.append({
                    "actividad": actividad,
                    "comision":  codigo,
                    "modalidad": modalidad,
                    "horario":   horario
                })

    return {"comisiones": resultados}