import { useEffect, useState } from "react"
import CarruselArrows from "./CarruselArrows"
import CarruselIndicators from "./CarruselIndicators"
import CarruselTrack from "./CarruselTrack"

const AUTOPLAY_INTERVAL_MS = 5000

const slides = [
  {
    id: 1,
    src: "/carrusel/Materias.jpg",
    href: "/materias",
    alt: "foto de la Universidad de quilmes",
    title: "Materias",
    description: "Gestiona las materias de la universidad",
  },
  {
    id: 2,
    src: "/carrusel/Carreras.jpg",
    href: "/carreras",
    alt: "carreras de la universidad",
    title: "Carreras",
    description:
      "Explora las diferentes carreras disponibles en la universidad",
  },
  {
    id: 3,
    src: "/carrusel/Comisiones.jpg",
    href: "/comisiones/mostrar",
    alt: "aula de la universidad",
    title: "Comisiones",
    description:
      "Accede a la información detallada de las comisiones de la universidad",
  },
]

export default function Carrusel() {
  const [activeIndex, setActiveIndex] = useState(0)

  const goToNext = () => {
    setActiveIndex((prevIndex) => (prevIndex + 1) % slides.length)
  }

  const goToPrev = () => {
    setActiveIndex(
      (prevIndex) => (prevIndex - 1 + slides.length) % slides.length
    )
  }

  useEffect(() => {
    const timeoutId = setTimeout(() => {
      goToNext()
    }, AUTOPLAY_INTERVAL_MS)

    return () => clearTimeout(timeoutId)
  }, [activeIndex])

  return (
    <section className="mx-auto w-full max-w-4xl">
      <div className="relative">
        <CarruselTrack slides={slides} activeIndex={activeIndex} />
        <CarruselArrows onPrev={goToPrev} onNext={goToNext} />
      </div>
      <CarruselIndicators
        total={slides.length}
        activeIndex={activeIndex}
        onSelect={setActiveIndex}
      />
    </section>
  )
}
