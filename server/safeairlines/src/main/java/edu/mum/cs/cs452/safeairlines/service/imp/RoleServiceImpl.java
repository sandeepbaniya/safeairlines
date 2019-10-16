package edu.mum.cs.cs452.safeairlines.service.imp;

import edu.mum.cs.cs452.safeairlines.model.Role;
import edu.mum.cs.cs452.safeairlines.repository.RoleRepository;
import edu.mum.cs.cs452.safeairlines.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getById(long id) {
        return roleRepository.findById(id).get();
    }
}