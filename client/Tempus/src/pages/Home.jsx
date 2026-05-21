import Carrusel from "../components/Ui/Carrusel/Carrusel"
//vza_0e7628ef880f4abcab9fbd43d1a02580
export default function Home() {
  return (
    <section className="flex flex-col items-center justify-center min-h-[70vh] text-center px-6">
      <iframe
        src="http://localhost:5173/testimonial?apiKey=vza_26c22dc8852d48dfbcab6f62fe4bb1cd"
        title="Formulario de testimonios"
        frameBorder="0"
        loading="lazy"
        style={{
          width: "100%",
          minHeight: "100vh",
          marginBottom: "2rem",
          height: "auto",
          border: "none",
        }}
      ></iframe>
      <h1 className="text-4xl md:text-5xl font-bold text-white tracking-tight">
        Bienvenido a <span className="text-red-500">Tempus</span>
      </h1>

      <p className="mt-4 text-lg text-gray-400 max-w-xl leading-relaxed">
        Gestiona tu recorrido académico en la UNQ. Registra tus materias,
        explora el mapa de correlativas y planifica tu cursada para los próximos
        cuatrimestres.
      </p>

      <div className="mt-8 h-px w-40 bg-gradient-to-r from-transparent via-red-600 to-transparent" />

      <p className="mt-6 text-sm text-gray-500">
        Usa el menú o el carrusel para comenzar.
      </p>

      <iframe
        src="http://localhost:5173/testimonial/published?apiKey=vza_26c22dc8852d48dfbcab6f62fe4bb1cd"
        title="Carrusel de testimonios"
        frameBorder="0"
        loading="lazy"
        style={{
          width: "100%",
          minHeight: "100vh",
          height: "auto",
          border: "none",
        }}
      ></iframe>
    </section>
  )
}
