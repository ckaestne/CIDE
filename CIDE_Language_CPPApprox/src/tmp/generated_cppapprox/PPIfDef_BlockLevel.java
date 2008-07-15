package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PPIfDef_BlockLevel extends GenASTNode {
  public PPIfDef_BlockLevel(IfDefLine ifDefLine, Sequence_CodeUnit_InBlock sequence_CodeUnit_InBlock, ArrayList<IfElseIf_BlockLevel> ifElseIf_BlockLevel, Sequence_CodeUnit_InBlock sequence_CodeUnit_InBlock1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<IfDefLine>("ifDefLine", ifDefLine),
      new PropertyOne<Sequence_CodeUnit_InBlock>("sequence_CodeUnit_InBlock", sequence_CodeUnit_InBlock),
      new PropertyZeroOrMore<IfElseIf_BlockLevel>("ifElseIf_BlockLevel", ifElseIf_BlockLevel),
      new PropertyZeroOrOne<Sequence_CodeUnit_InBlock>("sequence_CodeUnit_InBlock1", sequence_CodeUnit_InBlock1)
    }, firstToken, lastToken);
  }
  public PPIfDef_BlockLevel(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PPIfDef_BlockLevel(cloneProperties(),firstToken,lastToken);
  }
  public IfDefLine getIfDefLine() {
    return ((PropertyOne<IfDefLine>)getProperty("ifDefLine")).getValue();
  }
  public Sequence_CodeUnit_InBlock getSequence_CodeUnit_InBlock() {
    return ((PropertyOne<Sequence_CodeUnit_InBlock>)getProperty("sequence_CodeUnit_InBlock")).getValue();
  }
  public ArrayList<IfElseIf_BlockLevel> getIfElseIf_BlockLevel() {
    return ((PropertyZeroOrMore<IfElseIf_BlockLevel>)getProperty("ifElseIf_BlockLevel")).getValue();
  }
  public Sequence_CodeUnit_InBlock getSequence_CodeUnit_InBlock1() {
    return ((PropertyZeroOrOne<Sequence_CodeUnit_InBlock>)getProperty("sequence_CodeUnit_InBlock1")).getValue();
  }
}
