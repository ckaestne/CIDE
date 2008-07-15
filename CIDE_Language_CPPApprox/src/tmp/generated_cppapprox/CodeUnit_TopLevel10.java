package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CodeUnit_TopLevel10 extends CodeUnit_TopLevel {
  public CodeUnit_TopLevel10(CPPClass cPPClass, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CPPClass>("cPPClass", cPPClass)
    }, firstToken, lastToken);
  }
  public CodeUnit_TopLevel10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CodeUnit_TopLevel10(cloneProperties(),firstToken,lastToken);
  }
  public CPPClass getCPPClass() {
    return ((PropertyOne<CPPClass>)getProperty("cPPClass")).getValue();
  }
}
