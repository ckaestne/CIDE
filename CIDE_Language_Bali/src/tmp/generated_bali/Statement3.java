package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement3 extends Statement {
  public Statement3(JavacodeProduction javacodeProduction, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JavacodeProduction>("javacodeProduction", javacodeProduction)
    }, firstToken, lastToken);
  }
  public Statement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement3(cloneProperties(),firstToken,lastToken);
  }
  public JavacodeProduction getJavacodeProduction() {
    return ((PropertyOne<JavacodeProduction>)getProperty("javacodeProduction")).getValue();
  }
}
