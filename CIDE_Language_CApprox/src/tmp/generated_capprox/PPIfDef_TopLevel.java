package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PPIfDef_TopLevel extends GenASTNode {
  public PPIfDef_TopLevel(IfDefLine ifDefLine, Sequence_CodeUnit_TopLevel sequence_CodeUnit_TopLevel, ArrayList<IfElseIf_TopLevel> ifElseIf_TopLevel, Sequence_CodeUnit_TopLevel sequence_CodeUnit_TopLevel1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<IfDefLine>("ifDefLine", ifDefLine),
      new PropertyOne<Sequence_CodeUnit_TopLevel>("sequence_CodeUnit_TopLevel", sequence_CodeUnit_TopLevel),
      new PropertyZeroOrMore<IfElseIf_TopLevel>("ifElseIf_TopLevel", ifElseIf_TopLevel),
      new PropertyZeroOrOne<Sequence_CodeUnit_TopLevel>("sequence_CodeUnit_TopLevel1", sequence_CodeUnit_TopLevel1)
    }, firstToken, lastToken);
  }
  public PPIfDef_TopLevel(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PPIfDef_TopLevel(cloneProperties(),firstToken,lastToken);
  }
  public IfDefLine getIfDefLine() {
    return ((PropertyOne<IfDefLine>)getProperty("ifDefLine")).getValue();
  }
  public Sequence_CodeUnit_TopLevel getSequence_CodeUnit_TopLevel() {
    return ((PropertyOne<Sequence_CodeUnit_TopLevel>)getProperty("sequence_CodeUnit_TopLevel")).getValue();
  }
  public ArrayList<IfElseIf_TopLevel> getIfElseIf_TopLevel() {
    return ((PropertyZeroOrMore<IfElseIf_TopLevel>)getProperty("ifElseIf_TopLevel")).getValue();
  }
  public Sequence_CodeUnit_TopLevel getSequence_CodeUnit_TopLevel1() {
    return ((PropertyZeroOrOne<Sequence_CodeUnit_TopLevel>)getProperty("sequence_CodeUnit_TopLevel1")).getValue();
  }
}
