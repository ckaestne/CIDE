package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class op1 extends op {
  public op1(varop varop, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<varop>("varop", varop)
    }, firstToken, lastToken);
  }
  public op1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new op1(cloneProperties(),firstToken,lastToken);
  }
  public varop getVarop() {
    return ((PropertyOne<varop>)getProperty("varop")).getValue();
  }
}
