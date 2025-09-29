package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.entity.Bulletin;
import com.djiguiya.djiguiya.repository.BulletinRepository;
import com.djiguiya.djiguiya.repository.MatiereRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BulletinService {
    private BulletinRepository bulletinRepository;


    //create bulletin
    public Bulletin creatBulletin(Bulletin bulletin, ){
        Bulletin bulletin1 = new Bulletin();
        bulletin1.setAssociation();
        bulletin1.setMatiers()

    }
}
