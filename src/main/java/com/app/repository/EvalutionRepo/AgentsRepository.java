package com.app.repository.EvalutionRepo;

import com.app.entity.evalution.Agents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentsRepository extends JpaRepository<Agents, Long> {
}