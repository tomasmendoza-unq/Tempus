export default function CarruselSlide({ slide }) {
  return (
    <article className="relative h-64 w-full flex-shrink-0 md:h-80 lg:h-96">
      <a
        href={slide.href}
        target="_blank"
        rel="noreferrer"
        className="block h-full w-full"
      >
        <img
          src={slide.src}
          alt={slide.alt}
          className="h-full w-full object-cover bg-slate-900/60 p-8"
          loading="lazy"
        />

        <div className="absolute inset-x-0 bottom-0 bg-gradient-to-t from-slate-950/90 to-transparent p-5 text-left">
          <h3 className="text-lg font-semibold text-white md:text-xl">
            {slide.title}
          </h3>
          <p className="mt-1 text-sm text-slate-300 md:text-base">
            {slide.description}
          </p>
        </div>
      </a>
    </article>
  )
}
