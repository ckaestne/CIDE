package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constructorNaam extends patroonMain {
  public constructorNaam(naam naam, contrPatrParam contrPatrParam, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naam>("naam", naam),
      new PropertyZeroOrOne<contrPatrParam>("contrPatrParam", contrPatrParam)
    }, firstToken, lastToken);
  }
  public constructorNaam(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new constructorNaam(cloneProperties(),firstToken,lastToken);
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
  public contrPatrParam getContrPatrParam() {
    return ((PropertyZeroOrOne<contrPatrParam>)getProperty("contrPatrParam")).getValue();
  }
}
