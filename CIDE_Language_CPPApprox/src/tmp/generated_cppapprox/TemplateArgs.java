package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TemplateArgs extends GenASTNode {
  public TemplateArgs(TemplateArg templateArg, ArrayList<TemplateArg> templateArg1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TemplateArg>("templateArg", templateArg),
      new PropertyZeroOrMore<TemplateArg>("templateArg1", templateArg1)
    }, firstToken, lastToken);
  }
  public TemplateArgs(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TemplateArgs(cloneProperties(),firstToken,lastToken);
  }
  public TemplateArg getTemplateArg() {
    return ((PropertyOne<TemplateArg>)getProperty("templateArg")).getValue();
  }
  public ArrayList<TemplateArg> getTemplateArg1() {
    return ((PropertyZeroOrMore<TemplateArg>)getProperty("templateArg1")).getValue();
  }
}
