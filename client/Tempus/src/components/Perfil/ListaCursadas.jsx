import { useState } from "react";
import Modal from "../Ui/Modal/Modal"; 
import { HeaderCursadas } from "./HeaderCursadas";
import { CursadaItem } from "./CursadaItem";
import { HorarioModalContent } from "../Horario/HorarioModalContent";

export function ListaCursadas({ comisiones }) {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const horarioData = { comisiones: comisiones || [] };

  return (
    <section className="space-y-4">
      <HeaderCursadas onOpenModal={() => setIsModalOpen(true)} />

      <div className="bg-white rounded-xl border border-gray-100 shadow-sm overflow-hidden">
        {comisiones?.length > 0 ? (
          <div className="divide-y divide-gray-50">
            {comisiones.map((com) => (
              <CursadaItem key={com.comisionId} comision={com} />
            ))}
          </div>
        ) : (
          <div className="py-10 text-center text-gray-400 italic text-sm">
            No hay inscripciones registradas.
          </div>
        )}
      </div>

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
        <HorarioModalContent horarioData={horarioData} />
      </Modal>
    </section>
  );
}