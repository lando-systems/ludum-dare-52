package lando.systems.ld52.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import lando.systems.ld52.Assets;

import java.util.HashMap;
import java.util.Map;

public enum Feature {

      collar_a         (Category.clothes,   "collar-a-idle",   "collar")
    // TODO - eyes should be a separate thing? are we doing eye color vs eye-wear?
    , eyes_a           (Category.eye,       "eyes-a-idle",     "eyes")
    , glasses_a        (Category.eye,       "glasses-a",       "glasses")
    , glasses_star        (Category.eye,       "glasses-star",       "Elton Shades")
    , glasses_cop        (Category.eye,       "glasses-reflective",       "Cop Shades")
    , eyepatch_a       (Category.eye,       "eyepatch-a",      "eye patch")

    , nose_normal      (Category.nose,      "feature-blank",   "normal nose")
    , nose_clown       (Category.nose,      "nose-clown",      "clown nose")

    , tongue           (Category.mouth,     "tongue",          "tongue out")

    , clean_shaven     (Category.hair_face, "feature-blank",   "clean shaven")
    , mustache_a       (Category.hair_face, "mustache-a",      "mustache")
    , beard_beard       (Category.hair_face, "beard-beard",      "Full Beard")
    , beard_goatee       (Category.hair_face, "beard-goatee",      "Goatee")
    , beard_soulpatch       (Category.hair_face, "beard-soulpatch",      "Soulpatch (lol)")
//    ,beard_beard       (Category.hair_face, "beard-",      "")

    , hair_bald        (Category.hair_head, "feature-blank",   "bald")
    , hair_long_brown  (Category.hair_head, "hair-long-brown",  "long brown hair")
    , hair_short_black (Category.hair_head, "hair-short-black", "short black hair")
    , hair_ponytail (Category.hair_head, "hair-ariana-ponytail", "Ari's Pony")
    , hair_balding (Category.hair_head, "hair-balding", "Balding")
    , hair_curly_black (Category.hair_head, "hair-curly-black", "Natural")
    , hair_curly_red (Category.hair_head, "hair-curly-red", "Red Natural")
    , hair_leia (Category.hair_head, "hair-leia-brown", "Princess Buns")
    , hair_long_blue (Category.hair_head, "hair-long-blue", "Long Blue")
    , hair_long_darkteal (Category.hair_head, "hair-long-darkteal", "Long Teal")
    , hair_manbun (Category.hair_head, "hair-manbun", "Man-bun (ew)")
    , hair_mohawk_blue (Category.hair_head, "hair-mohawk-blue", "Blue Hawk")
    , hair_mohawk_green (Category.hair_head, "hair-mohawk-green", "Green Hawk")
    , hair_mohawk_red (Category.hair_head, "hair-mohawk-red", "Red Hawk")
    , hair_powdered_wig (Category.hair_head, "hair-powdered-wig", "Hamilton Cosplay")
//    , hair_ (Category.hair_head, "hair-", "hair")
//    , hair_ (Category.hair_head, "hair-", "hair")

    , hat_beret_green (Category.hair_head, "hat-beret-green", "Green Beret")
    , hat_beret_red (Category.hair_head, "hat-beret-red", "Raspberry Beret")
    , hat_fez (Category.hair_head, "hat-fez", "Fez")
    , hat_hardhat (Category.hair_head, "hat-hardhat", "Hardhat")
    , hat_heisenberg (Category.hair_head, "hat-heisenberg", "Heisenberg")
    , hat_lombardi (Category.hair_head, "hat-lombardi", "Generic Hat")
    , hat_sheriff (Category.hair_head, "hat-sheriff", "Sheriff")
    , hat_summer (Category.hair_head, "hat-summer", "White Lady Hat")
//    , hat_ (Category.hat, "hat-", "Hat")
//    , cigarette        (Category.mouth, "cigarette", "cigarette") // NOTE - not legible onscreen so removing it as a feature option
    ;

    public enum Category {
          clothes   (0)
        , eye       (1)
        , nose      (2)
        , mouth     (3)
        , hair_face (4)
        , hair_head (5)
//        , hat       (6) // TODO - no 'hat' features yet
//        , neck      (7) // TODO - no 'neck' features yet (like necklace or tie or something)
        ;
        public final int layer;
        Category(int layer) {
            this.layer = layer;
        }
    }

    public static final String prefix = "faces/test/";
    public final Category category;
    public final String regionsName;
    public final String displayName;

    Feature(Category category, String regionsName, String displayName) {
        this.category = category;
        this.regionsName = prefix + regionsName;
        this.displayName = displayName;
    }

    public static Animation<TextureRegion> get(Assets assets, Feature feature) {
        return assets.features.get(feature);
    }

    private static final Map<Category, Array<Feature>> featuresByCategory = new HashMap<>();
    public static Feature getRandomFrom(Category category) {
        if (featuresByCategory.isEmpty()) {
            for (Feature feature : Feature.values()) {
                featuresByCategory.putIfAbsent(feature.category, new Array<>());
                featuresByCategory.get(feature.category).add(feature);
            }
        }
        Array<Feature> features = featuresByCategory.get(category);
        if (features == null || features.isEmpty()) {
            Gdx.app.log("feature", "no features for category: " + category.name());
        }
        return features.get(MathUtils.random(0, features.size - 1));
    }

    public static Feature getFeature(Feature feature, Category category) {
        return feature != null ? feature : getRandomFrom(category);
    }
}
