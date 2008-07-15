package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TemplateDecl extends GenASTNode {
  public TemplateDecl(TemplateArgs templateArgs, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<TemplateArgs>("templateArgs", templateArgs)
    }, firstToken, lastToken);
  }
  public TemplateDecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TemplateDecl(cloneProperties(),firstToken,lastToken);
  }
  public TemplateArgs getTemplateArgs() {
    return ((PropertyZeroOrOne<TemplateArgs>)getProperty("templateArgs")).getValue();
  }
}
