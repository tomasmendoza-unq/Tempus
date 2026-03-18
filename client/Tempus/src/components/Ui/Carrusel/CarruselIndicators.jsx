export default function CarruselIndicators({ total, activeIndex, onSelect }) {
  return (
    <div className="mt-4 flex items-center justify-center gap-2">
      {Array.from({ length: total }).map((_, index) => (
        <button
          key={index}
          type="button"
          onClick={() => onSelect(index)}
          aria-label={`Ir al slide ${index + 1}`}
          className={`h-2.5 rounded-full transition-all duration-300 ${
            activeIndex === index
              ? "w-8 bg-red-500"
              : "w-2.5 bg-slate-500 hover:bg-slate-300"
          }`}
        />
      ))}
    </div>
  )
}
