const crearMateria = async (formData) => {
  try {
      const response = await fetch('/api/materia/crear', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });
      
      const text = await response.text();
      console.log('Response status:', response.status);
      console.log('Response body:', text);
      
      if (!response.ok) {
        throw new Error(`Error ${response.status}: ${text}`);
      }
      
      const data = JSON.parse(text);
      console.log("Creada:", data);
    } catch (error) {
      console.error("Error completo:", error);
    }
};

const traerMateria = async (idMateria) => {
  const response = await fetch(`${import.meta.env.VITE_API_URL}/materia/${idMateria}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },  
   
    });
    return response.json();
};

export {crearMateria, traerMateria};