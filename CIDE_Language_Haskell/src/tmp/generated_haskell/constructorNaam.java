package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

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
