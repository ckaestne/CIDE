package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimitiveRewriteNode extends Rewrite {
  public PrimitiveRewriteNode(Primitive primitive1, PrimitiveRewrite primitiveRewrite, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Primitive>("primitive1", primitive1),
      new PropertyOne<PrimitiveRewrite>("primitiveRewrite", primitiveRewrite)
    }, firstToken, lastToken);
  }
  public PrimitiveRewriteNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimitiveRewriteNode(cloneProperties(),firstToken,lastToken);
  }
  public Primitive getPrimitive1() {
    return ((PropertyOne<Primitive>)getProperty("primitive1")).getValue();
  }
  public PrimitiveRewrite getPrimitiveRewrite() {
    return ((PropertyOne<PrimitiveRewrite>)getProperty("primitiveRewrite")).getValue();
  }
}
