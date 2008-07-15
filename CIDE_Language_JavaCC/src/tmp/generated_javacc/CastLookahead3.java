package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CastLookahead3 extends CastLookahead {
  public CastLookahead3(Type type1, CastLAOp castLAOp, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Type>("type1", type1),
      new PropertyOne<CastLAOp>("castLAOp", castLAOp)
    }, firstToken, lastToken);
  }
  public CastLookahead3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CastLookahead3(cloneProperties(),firstToken,lastToken);
  }
  public Type getType1() {
    return ((PropertyOne<Type>)getProperty("type1")).getValue();
  }
  public CastLAOp getCastLAOp() {
    return ((PropertyOne<CastLAOp>)getProperty("castLAOp")).getValue();
  }
}
