package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CodeUnit_TopLevel9 extends CodeUnit_TopLevel {
  public CodeUnit_TopLevel9(CPPClassForwardDecl cPPClassForwardDecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CPPClassForwardDecl>("cPPClassForwardDecl", cPPClassForwardDecl)
    }, firstToken, lastToken);
  }
  public CodeUnit_TopLevel9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CodeUnit_TopLevel9(cloneProperties(),firstToken,lastToken);
  }
  public CPPClassForwardDecl getCPPClassForwardDecl() {
    return ((PropertyOne<CPPClassForwardDecl>)getProperty("cPPClassForwardDecl")).getValue();
  }
}
