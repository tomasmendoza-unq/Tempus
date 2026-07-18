export default function Tabs({ tabs, currentTab, onTabChange }) {
  return (
    <div className="flex justify-center">
      <div className="inline-flex rounded-xl border border-gray-200 bg-white shadow-sm overflow-hidden">
        {tabs.map((tab, index) => (
          <button
            key={index}
            onClick={() => onTabChange(index, tab)}
            className={`px-6 py-2.5 text-sm font-medium transition focus:outline-none ${
              currentTab === index
                ? "bg-red-950 text-white"
                : "bg-white text-gray-600 hover:bg-gray-50"
            } ${index < tabs.length - 1 ? "border-r border-gray-200" : ""}`}
          >
            {tab.label}
          </button>
        ))}
      </div>
    </div>
  )
}
