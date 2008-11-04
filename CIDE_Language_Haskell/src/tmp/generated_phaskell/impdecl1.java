package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class impdecl1 extends impdecl {
  public impdecl1(ASTTextNode text366, modid modid, modid modid1, impspec impspec, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text366", text366),
      new PropertyOne<modid>("modid", modid),
      new PropertyZeroOrOne<modid>("modid1", modid1),
      new PropertyZeroOrOne<impspec>("impspec", impspec)
    }, firstToken, lastToken);
  }
  public impdecl1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new impdecl1(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText366() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text366")).getValue();
  }
  public modid getModid() {
    return ((PropertyOne<modid>)getProperty("modid")).getValue();
  }
  public modid getModid1() {
    return ((PropertyZeroOrOne<modid>)getProperty("modid1")).getValue();
  }
  public impspec getImpspec() {
    return ((PropertyZeroOrOne<impspec>)getProperty("impspec")).getValue();
  }
}
