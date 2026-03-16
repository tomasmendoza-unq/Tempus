import { useState } from "react";
import { toast } from "react-toastify";
import { previewImportService } from "../services/importService";

const useImport = () => {
  const [materias, setMaterias] = useState([]);
  const [cargando, setCargando] = useState(false);

  const previsualizarPDF = async (file) => {
    if (!file) {
      toast.warn("Por favor, seleccioná un archivo PDF");
      return;
    }

    setCargando(true);
    const formData = new FormData();
    formData.append('pdf', file);

    try {
      const data = await previewImportService(formData);

      const content = data.content || [];
      setMaterias(content);
      
      if (content.length === 0) {
        toast.info("No se encontraron materias en el archivo");
      } else {
        toast.success("PDF procesado correctamente");
      }
      
      return content;
    } catch (error) {
      console.error("Error en preview:", error);
      toast.error(error.response?.data?.message || "Error al procesar el PDF");
      throw error;
    } finally {
      setCargando(false);
    }
  };

  const limpiarPreview = () => {
    setMaterias([]);
  };

  return { 
    materias, 
    setMaterias,
    cargando, 
    previsualizarPDF,
    limpiarPreview 
  };
};

export default useImport;