export default function AuthLayout({ title, children }) {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 p-4">
      <div className="bg-white p-8 rounded-xl shadow-lg w-full max-w-md space-y-4 border border-gray-100">
        <h2 className="text-2xl font-bold text-red-950 text-center">
          {title}
        </h2>
        {children}
      </div>
    </div>
  );
}