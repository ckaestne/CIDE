package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CodeUnit_TopLevel5 extends CodeUnit_TopLevel {
  public CodeUnit_TopLevel5(TemplateDecl templateDecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TemplateDecl>("templateDecl", templateDecl)
    }, firstToken, lastToken);
  }
  public CodeUnit_TopLevel5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CodeUnit_TopLevel5(cloneProperties(),firstToken,lastToken);
  }
  public TemplateDecl getTemplateDecl() {
    return ((PropertyOne<TemplateDecl>)getProperty("templateDecl")).getValue();
  }
}
