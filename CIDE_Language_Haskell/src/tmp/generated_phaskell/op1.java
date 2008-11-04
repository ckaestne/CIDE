package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

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
