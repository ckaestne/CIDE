package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CastLAOp8 extends CastLAOp {
  public CastLAOp8(Literal literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Literal>("literal", literal)
    }, firstToken, lastToken);
  }
  public CastLAOp8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CastLAOp8(cloneProperties(),firstToken,lastToken);
  }
  public Literal getLiteral() {
    return ((PropertyOne<Literal>)getProperty("literal")).getValue();
  }
}
