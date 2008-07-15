package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExternDec extends CodeUnit_TopLevel {
  public ExternDec(ExternDecl externDecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ExternDecl>("externDecl", externDecl)
    }, firstToken, lastToken);
  }
  public ExternDec(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExternDec(cloneProperties(),firstToken,lastToken);
  }
  public ExternDecl getExternDecl() {
    return ((PropertyOne<ExternDecl>)getProperty("externDecl")).getValue();
  }
}
